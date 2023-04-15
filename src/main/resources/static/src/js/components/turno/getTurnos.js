import {getAllTurnos} from '../../logic/crudTurnos.js';

const mock = [
  {
    id: 1,
    fecha: '4/5/2023',
    hora: '12:00:00',
    odontologoId: 1,
    pacienteId: 1,
  },
  {
    id: 2,
    fecha: '4/5/2023',
    hora: '12:00:00',
    odontologoId: 1,
    pacienteId: 1,
  },
  {
    id: 3,
    fecha: '4/5/2023',
    hora: '12:00:00',
    odontologoId: 1,
    pacienteId: 1,
  },
  {
    id: 4,
    fecha: '4/5/2023',
    hora: '12:00:00',
    odontologoId: 1,
    pacienteId: 1,
  },
];

window.addEventListener('DOMContentLoaded', () => {
  const listaPacientes = document.querySelector('#lista-turnos');
  const tableFoot = document.querySelector('#table-foot');
  renderizarTurnos();

  async function renderizarTurnos() {
    const turnos = await getAllTurnos();

    turnos.forEach((turno) => {
      renderizarTurno(turno);
    });

    tableFoot.innerHTML += `
      <tr>
        <th scope="row" colspan="1">Total de Turnos</th>
        <td colspan="2">${turnos.length}</td>
      </tr>`;
  }

  async function renderizarTurno({id, fechaHora, odontologo, paciente}) {
    const dateOfTurno = new Date(fechaHora);
    listaPacientes.innerHTML += `
        <tr>
          <th scope="row">${id}</th>
          <td id="celda-fecha">${dateOfTurno.toLocaleDateString()}</td>
          <td id="celda-hora">${dateOfTurno.getHours()} Hs</td>
          <td id="celda-odontologoId">${odontologo.nombre} ${
      odontologo.apellido
    } - ${odontologo.matricula}</td>
          <td id="celda-pacienteId">${paciente.nombre} ${paciente.apellido} - ${
      paciente.dni
    }</td>
          
          <td>
            <button type="button" title="Modificar turno" id=${id} class="change"><i id=${id} class="fa-solid fa-rotate-left"></i></button>
          </td>
          <td>
            <button type="button" title="Eliminar turno" id=${id} class="delete"><i id=${id} class="fa-regular fa-trash-can"></i></button>
          </td>
        </tr>`;
  }
});
