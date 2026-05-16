const API_BASE = 'http://localhost:8080/api'

async function request(path, options = {}) {
  const res = await fetch(`${API_BASE}${path}`, {
    headers: { 'Content-Type': 'application/json', ...options.headers },
    ...options
  })
  if (!res.ok) {
    const text = await res.text()
    throw new Error(text || res.statusText)
  }
  return res.status === 204 ? null : res.json()
}

export const api = {
  register: (body) => request('/users/register', { method: 'POST', body: JSON.stringify(body) }),
  login: (body) => request('/users/login', { method: 'POST', body: JSON.stringify(body) }),
  getFoods: () => request('/foods'),
  getFood: (id) => request(`/foods/${id}`),
  createOrder: (body) => request('/orders', { method: 'POST', body: JSON.stringify(body) }),
  getOrders: (userId) => request(`/orders?userId=${userId}`),
  getNotifications: (userId) => request(`/notifications?userId=${userId}`)
}
