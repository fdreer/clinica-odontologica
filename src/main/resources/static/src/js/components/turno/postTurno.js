import {getAllOdontologos} from '../../logic/crudOdontologos.js';
import {getAllPacientes} from '../../logic/crudPacientes.js';
import {createTurno} from '../../logic/crudTurnos.js';

const mockOdontologos = [
  {
    id: 1,
    nombre: 'Franco',
    apellido: 'Dreer',
    matricula: '123abc',
  },
  {
    id: 2,
    nombre: 'Franco',
    apellido: 'Dreer',
    matricula: '123abc',
  },
  {
    id: 3,
    nombre: 'Franco',
    apellido: 'Dreer',
    matricula: '123abc',
  },
  {
    id: 4,
    nombre: 'Franco',
    apellido: 'Dreer',
    matricula: '123abc',
  },
];
const mockPacientes = [
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

window.addEventListener('DOMContentLoaded', async () => {
  const dById = (id) => document.getElementById(`${id}`);
  const odontologoOptions = document.getElementById('odontologo-options');
  const pacienteOptions = document.getElementById('paciente-options');
  const $form = document.querySelector('form');
  renderizarSelects();

  async function renderizarSelects() {
    const allOdontologos = await getAllOdontologos();
    const allPacientes = await getAllPacientes();

    allOdontologos.forEach((odontologo) => {
      renderizarOptionsOdontologo(odontologo);
    });

    allPacientes.forEach((paciente) => renderizarOptionsPaciente(paciente));
  }

  function renderizarOptionsOdontologo(odontologo) {
    odontologoOptions.innerHTML += `
        <option value="${odontologo.id}">${odontologo.nombre} ${odontologo.apellido} - ${odontologo.matricula}</option>
      `;
  }

  function renderizarOptionsPaciente(paciente) {
    pacienteOptions.innerHTML += `
        <option value="${paciente.id}">${paciente.nombre} ${paciente.apellido} - ${paciente.dni}</option>
      `;
  }

  $form.addEventListener('submit', async (e) => {
    e.preventDefault();
    console.log(document.getElementById('fecha').value);

    const dataTurno = {
      // ERROR:
      // verificar: al registrar la hora, la persiste
      // 3 hs mas de lo que pongo en el front
      fechaHora: new Date(`${dById('fecha').value}, ${dById('hora').value}`),
      odontologo: {id: odontologoOptions.value},
      paciente: {id: pacienteOptions.value},
    };

    const turnoCreado = await createTurno({body: dataTurno});
    console.log(turnoCreado);
    if (turnoCreado.status === 400) {
      Swal.fire({
        icon: 'error',
        title: turnoCreado.error,
        text: turnoCreado.message,
      });
    } else {
      Toast.fire({
        icon: 'success',
        title: 'Turno registrado',
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
