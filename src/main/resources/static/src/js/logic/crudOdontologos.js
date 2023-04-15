import {helpHttp} from '../helpers/helpHttp.js';

export async function createOdontologo({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return await helpHttp().post('/odontologo', options);
}

export async function getOdontologoById(id) {
  return await helpHttp().get(`/odontologo/${id}`);
}

export async function getAllOdontologos() {
  return await helpHttp().get('/odontologo');
}

export async function updateOdontologo({body}) {
  const options = {
    body: body,
    headers: {
      'Content-type': 'application/json',
    },
  };
  return await helpHttp().put('/odontologo', options);
}

export async function deleteOdontologoById(id) {
  await helpHttp().del(`/odontologo/${id}`);
}
