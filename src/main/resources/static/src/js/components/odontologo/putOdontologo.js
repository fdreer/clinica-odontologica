import {
  getOdontologoById,
  updateOdontologo,
} from '../../logic/crudOdontologos.js';

window.addEventListener('DOMContentLoaded', () => {
  const listaOdontologos = document.querySelector('#lista-odontologos');

  listaOdontologos.addEventListener('click', async (e) => {
    if (e.target.matches('.change') || e.target.matches('.change *')) {
      const odontologo = await getOdontologoById(e.target.id);
      modificarDatos(odontologo);
      console.log(odontologo);
    }
  });

  async function modificarDatos({id, nombre, apellido, matricula}) {
    const {value: formValues} = await Swal.fire({
      title: 'Modificar usuario',
      html:
        `<input id="swal-input1" class="swal2-input" value=${nombre}>` +
        `<input id="swal-input2" class="swal2-input" value=${apellido}>` +
        `<input id="swal-input3" class="swal2-input" value=${matricula}>`,
      focusConfirm: false,
      preConfirm: () => {
        return {
          id: id,
          nombre: document.getElementById('swal-input1').value.toUpperCase(),
          apellido: document.getElementById('swal-input2').value.toUpperCase(),
          matricula: document.getElementById('swal-input3').value.toUpperCase(),
        };
      },
    });

    if (formValues) {
      const odontologoChange = await updateOdontologo({body: formValues});

      window.location.reload();
    }
  }
});
