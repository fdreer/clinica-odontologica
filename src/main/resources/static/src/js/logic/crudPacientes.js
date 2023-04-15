import {helpHttp} from '../helpers/helpHttp.js';

export async function getAllPacientes() {
  return await helpHttp().get('/paciente');
}

export async function deletePacienteById(id) {
  await helpHttp().del(`/paciente/${id}`);
}

export async function getPacienteById(id) {
  return await helpHttp().get(`/paciente/${id}`);
}

export async function updatePaciente({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return await helpHttp().put(`/paciente`, options);
}

export async function createPaciente({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return helpHttp().post('/paciente', options);
}

export async function createDomicilio({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return helpHttp().post('/domicilio', options);
}
