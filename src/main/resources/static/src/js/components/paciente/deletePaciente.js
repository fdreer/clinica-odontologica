import {
  deletePacienteById,
  getAllPacientes,
} from '../../logic/crudPacientes.js';

window.addEventListener('DOMContentLoaded', () => {
  const listaPacientes = document.querySelector('#lista-pacientes');
  const tableFoot = document.querySelector('#table-foot');

  listaPacientes.addEventListener('click', async (e) => {
    if (e.target.matches('.delete') || e.target.matches('.delete *')) {
      await deletePacienteById(e.target.id);
      renderizarPacientes();
    }
  });

  async function renderizarPacientes() {
    const pacientes = await getAllPacientes();
    listaPacientes.innerHTML = '';
    tableFoot.innerHTML = '';

    pacientes.forEach((paciente) => {
      renderizarPaciente(paciente);
    });

    tableFoot.innerHTML += `
    <tr>
      <th scope="row" colspan="1">Total de Pacientes</th>
      <td colspan="2">${pacientes.length}</td>
    </tr>`;
  }

  function renderizarPaciente({id, nombre, apellido, dni}) {
    listaPacientes.innerHTML += `
        <tr>
          <th scope="row">${id}</th>
          <td>${nombre}</td>
          <td>${apellido}</td>
          <td>${dni}</td>
          <td>
            <button type="button" title="Modificar paciente" id=${id} class="change"><i id=${id} class="fa-solid fa-rotate-left"></i></button>
          </td>
          <td>
            <button type="button" title="Eliminar paciente" id=${id} class="delete"><i id=${id} class="fa-regular fa-trash-can"></i></button>
          </td>
        </tr>`;
  }
});
