import {getAllPacientes} from '../../logic/crudPacientes.js';
const mock = [
  {
    id: 1,
    nombre: 'Franco',
    apellido: 'Dreer',
    dni: '42488562',
  },
  {
    id: 2,
    nombre: 'Guillermo',
    apellido: 'Dreer',
    dni: '21311435',
  },
  {
    id: 3,
    nombre: 'Maria Luz',
    apellido: 'Dreer',
    dni: '5000000',
  },
  {
    id: 3,
    nombre: 'Maria Luz',
    apellido: 'Dreer',
    dni: '5000000',
  },
];

window.addEventListener('DOMContentLoaded', () => {
  const listaPacientes = document.querySelector('#lista-pacientes');
  const tableFoot = document.querySelector('#table-foot');
  renderizarPacientes();

  async function renderizarPacientes() {
    const pacientes = await getAllPacientes();
    // listaPacientes.innerHTML = '';

    pacientes.forEach((paciente) => {
      renderizarPaciente(paciente);
    });

    tableFoot.innerHTML += `
      <tr>
        <th scope="row" colspan="1">Total de Pacientes</th>
        <td colspan="2">${pacientes.length}</td>
      </tr>`;
  }

  function renderizarPaciente({id, nombre, apellido, dni, fechaIngreso}) {
    listaPacientes.innerHTML += `
        <tr>
          <th scope="row">${id}</th>
          <td id="celda-nombre">${nombre}</td>
          <td id="celda-apellido">${apellido}</td>
          <td id="celda-dni">${dni}</td>
          <td id="celda-dni">${fechaIngreso}</td>
          <td>
            <button type="button" title="Modificar paciente" id=${id} class="change"><i id=${id} class="fa-solid fa-rotate-left"></i></button>
          </td>
          <td>
            <button type="button" title="Eliminar paciente" id=${id} class="delete"><i id=${id} class="fa-regular fa-trash-can"></i></button>
          </td>
        </tr>`;
  }
});
