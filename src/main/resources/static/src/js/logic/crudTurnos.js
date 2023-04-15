import {helpHttp} from '../helpers/helpHttp.js';

export async function getAllTurnos() {
  return await helpHttp().get('/turno');
}

export async function deleteTurnoById(id) {
  await helpHttp().del(`/turno/${id}`);
}

export async function getTurnoById(id) {
  return await helpHttp().get(`/turno/${id}`);
}

export async function updateTurno({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return await helpHttp().put(`/turno`, options);
}

export async function createTurno({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return helpHttp().post('/turno', options);
}
