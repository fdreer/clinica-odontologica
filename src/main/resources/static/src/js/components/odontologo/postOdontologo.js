import {createOdontologo} from '../../logic/crudOdontologos.js';

window.addEventListener('DOMContentLoaded', () => {
  const dById = (id) => document.getElementById(`${id}`);
  const $form = document.querySelector('form');

  $form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const dataOdontologo = {
      nombre: dById('nombre').value.toUpperCase(),
      apellido: dById('apellido').value.toUpperCase(),
      matricula: dById('matricula').value.toUpperCase(),
    };

    const resServer = await createOdontologo({body: dataOdontologo});
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
        title: 'Odontologo registrado',
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
