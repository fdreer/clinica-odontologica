import {getAllOdontologos} from '../../logic/crudOdontologos.js';
window.addEventListener('DOMContentLoaded', () => {
  const listaOdontologos = document.querySelector('#lista-odontologos');
  const tableFoot = document.querySelector('#table-foot');
  renderizarOdontologos();

  async function renderizarOdontologos() {
    const odontologos = await getAllOdontologos();
    // listaPacientes.innerHTML = '';

    odontologos.forEach((odontologo) => {
      renderizarOdontologo(odontologo);
    });

    tableFoot.innerHTML += `
      <tr>
        <th scope="row" colspan="1">Total de Pacientes</th>
        <td colspan="2">${odontologos.length}</td>
      </tr>`;
  }

  function renderizarOdontologo({id, nombre, apellido, matricula}) {
    listaOdontologos.innerHTML += `
        <tr>
          <th scope="row">${id}</th>
          <td id="celda-nombre">${nombre}</td>
          <td id="celda-apellido">${apellido}</td>
          <td id="celda-matricula">${matricula}</td>
          <td>
            <button type="button" title="Modificar odontologo" id=${id} class="change"><i id=${id} class="fa-solid fa-rotate-left"></i></button>
          </td>
          <td>
            <button type="button" title="Eliminar odontologo" id=${id} class="delete"><i id=${id} class="fa-regular fa-trash-can"></i></button>
          </td>
        </tr>`;
  }
});
