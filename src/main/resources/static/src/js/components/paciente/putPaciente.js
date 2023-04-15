import {getPacienteById, updatePaciente} from '../../logic/crudPacientes.js';

window.addEventListener('DOMContentLoaded', () => {
  const listaPacientes = document.querySelector('#lista-pacientes');

  listaPacientes.addEventListener('click', async (e) => {
    if (e.target.matches('.change') || e.target.matches('.change *')) {
      const paciente = await getPacienteById(e.target.id);
      modificarDatos(paciente);
      console.log('change');
    }
  });

  async function modificarDatos({
    id,
    nombre,
    apellido,
    dni,
    fechaIngreso,
    domicilio,
  }) {
    const {value: formValues} = await Swal.fire({
      title: 'Modificar usuario',
      html:
        `<input id="swal-input1" class="swal2-input" value=${nombre}>` +
        `<input id="swal-input2" class="swal2-input" value=${apellido}>` +
        `<input id="swal-input3" class="swal2-input" value=${dni}>`,
      focusConfirm: false,
      preConfirm: () => {
        return {
          id: id,
          nombre: document.getElementById('swal-input1').value.toUpperCase(),
          apellido: document.getElementById('swal-input2').value.toUpperCase(),
          dni: document.getElementById('swal-input3').value.toUpperCase(),
          fechaIngreso: fechaIngreso,
          domicilio: domicilio,
        };
      },
    });

    if (formValues) {
      const pacienteChange = await updatePaciente({body: formValues});

      window.location.reload();
    }
  }
});
