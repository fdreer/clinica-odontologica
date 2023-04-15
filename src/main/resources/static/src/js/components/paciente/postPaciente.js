import {createDomicilio, createPaciente} from '../../logic/crudPacientes.js';

window.addEventListener('DOMContentLoaded', () => {
  const dById = (id) => document.getElementById(`${id}`);
  const $form = document.querySelector('form');

  $form.addEventListener('submit', async (e) => {
    e.preventDefault();
    const now = new Date().toLocaleDateString();

    // Primero rgistrar el domicilio y que devuelva el objeto creado para
    // extraer el id y poder asignarselo al paciente

    const dataDomicilio = {
      calle: dById('calle').value.toUpperCase(),
      numero: dById('numero').value.toUpperCase(),
      localidad: dById('localidad').value.toUpperCase(),
      provincia: dById('provincia').value.toUpperCase(),
    };

    const domicilioCreado = await createDomicilio({body: dataDomicilio});
    console.log(domicilioCreado);

    const dataPaciente = {
      nombre: dById('nombre').value.toUpperCase(),
      apellido: dById('apellido').value.toUpperCase(),
      dni: dById('dni').value.toUpperCase(),
      fechaIngreso: now,
      domicilio: {id: domicilioCreado.id},
    };
    console.log(dataPaciente);

    const resServer = await createPaciente({body: dataPaciente});
    console.log(resServer);
    if (resServer.status == 400) {
      Swal.fire({
        icon: 'error',
        title: resServer.error,
        text: resServer.message,
      });
    } else {
      Toast.fire({
        icon: 'success',
        title: 'Paciente registrado',
      });
    }
    resetForm();
  });

  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer);
      toast.addEventListener('mouseleave', Swal.resumeTimer);
    },
  });

  const resetForm = () => {
    $form.reset();
  };
});
