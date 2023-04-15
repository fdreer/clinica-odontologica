export const helpHttp = () => {
  // Que llevan las options:
  // const options = {
  //     method: method,
  //     headers: {
  //         "Content-type":"application/json",
  //         "authorization": jwt
  //     },
  //     body: JSON.stringify(body)
  // }

  const customFetch = (endpoint, options) => {
    const defaultHeader = {
      accept: 'application/json',
    };
    const controller = new AbortController();
    options.signal = controller.signal;

    options.method = options.method || 'GET';
    options.headers = options.headers
      ? {...defaultHeader, ...options.headers}
      : defaultHeader;

    options.body = JSON.stringify(options.body) || false;
    if (!options.body) delete options.body;

    setTimeout(() => controller.abort(), 2000);

    return fetch(endpoint, options)
      .then((res) => {
        console.log(res);
        return res.json();
        // return res.ok ? res.json() : Promise.reject();
      })
      .then((json) => {
        console.log(json);
        return json;
      })
      .catch((err) => {
        console.log(err);
        return err;
      });
  };

  const get = (url, options = {}) => customFetch(url, options);

  const post = (url, options = {}) => {
    options.method = 'POST';
    return customFetch(url, options);
  };

  const put = (url, options = {}) => {
    options.method = 'PUT';
    return customFetch(url, options);
  };

  const del = (url, options = {}) => {
    options.method = 'DELETE';
    return customFetch(url, options);
  };

  return {
    get,
    post,
    put,
    del,
  };
};
