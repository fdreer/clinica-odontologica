import {
  deleteOdontologoById,
  getAllOdontologos,
} from '../../logic/crudOdontologos.js';

window.addEventListener('DOMContentLoaded', () => {
  const listaOdontologos = document.querySelector('#lista-odontologos');
  const tableFoot = document.querySelector('#table-foot');

  listaOdontologos.addEventListener('click', async (e) => {
    if (e.target.matches('.delete') || e.target.matches('.delete *')) {
      // console.log(e.target.id);
      await deleteOdontologoById(e.target.id);
      renderizarOdontologos();
    }
  });

  async function renderizarOdontologos() {
    const odontologos = await getAllOdontologos();
    listaOdontologos.innerHTML = '';
    tableFoot.innerHTML = '';

    odontologos.forEach((odontologo) => {
      renderizarOdontologo(odontologo);
    });

    tableFoot.innerHTML += `
    <tr>
      <th scope="row" colspan="1">Total de Odontologos</th>
      <td colspan="2">${odontologos.length}</td>
    </tr>`;
  }

  function renderizarOdontologo({id, nombre, apellido, matricula}) {
    listaOdontologos.innerHTML += `
        <tr>
          <th scope="row">${id}</th>
          <td>${nombre}</td>
          <td>${apellido}</td>
          <td>${matricula}</td>
          <td>
            <button type="button" title="Modificar odontologo" id=${id} class="change"><i id=${id} class="fa-solid fa-rotate-left"></i></button>
          </td>
          <td>
            <button type="button" title="Eliminar odontologo" id=${id} class="delete"><i id=${id} class="fa-regular fa-trash-can"></i></button>
          </td>
        </tr>`;
  }
});
