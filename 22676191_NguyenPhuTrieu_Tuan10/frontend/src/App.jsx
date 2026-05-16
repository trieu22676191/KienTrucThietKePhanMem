import { useState, useEffect } from 'react'
import { api } from './api'

const STORAGE_KEY = 'food_delivery_user'

export default function App() {
  const [user, setUser] = useState(() => {
    const saved = localStorage.getItem(STORAGE_KEY)
    return saved ? JSON.parse(saved) : null
  })
  const [tab, setTab] = useState('foods')
  const [foods, setFoods] = useState([])
  const [orders, setOrders] = useState([])
  const [notifications, setNotifications] = useState([])
  const [message, setMessage] = useState(null)
  const [error, setError] = useState(null)

  const [loginForm, setLoginForm] = useState({ username: 'demo', password: '123456' })
  const [registerForm, setRegisterForm] = useState({ username: '', password: '', fullName: '' })

  useEffect(() => {
    if (user && tab === 'foods') loadFoods()
    if (user && tab === 'orders') loadOrders()
    if (user && tab === 'notifications') loadNotifications()
  }, [user, tab])

  const showError = (e) => setError(e.message || 'Có lỗi xảy ra')
  const clearMessages = () => { setError(null); setMessage(null) }

  const handleLogin = async (e) => {
    e.preventDefault()
    clearMessages()
    try {
      const data = await api.login(loginForm)
      setUser(data)
      localStorage.setItem(STORAGE_KEY, JSON.stringify(data))
      setMessage(`Xin chào, ${data.fullName || data.username}!`)
      setTab('foods')
    } catch (err) { showError(err) }
  }

  const handleRegister = async (e) => {
    e.preventDefault()
    clearMessages()
    try {
      const data = await api.register(registerForm)
      setUser(data)
      localStorage.setItem(STORAGE_KEY, JSON.stringify(data))
      setMessage('Đăng ký thành công!')
      setTab('foods')
    } catch (err) { showError(err) }
  }

  const handleLogout = () => {
    setUser(null)
    localStorage.removeItem(STORAGE_KEY)
    setTab('foods')
  }

  const loadFoods = async () => {
    try {
      setFoods(await api.getFoods())
    } catch (err) { showError(err) }
  }

  const loadOrders = async () => {
    try {
      setOrders(await api.getOrders(user.userId))
    } catch (err) { showError(err) }
  }

  const loadNotifications = async () => {
    try {
      setNotifications(await api.getNotifications(user.userId))
    } catch (err) { showError(err) }
  }

  const handleOrder = async (food) => {
    clearMessages()
    try {
      const order = await api.createOrder({
        userId: user.userId,
        foodId: food.id,
        quantity: 1
      })
      setMessage(`Đã tạo đơn #${order.id}. Thanh toán đang xử lý bất đồng bộ...`)
      setTimeout(loadOrders, 2000)
    } catch (err) { showError(err) }
  }

  if (!user) {
    return (
      <div>
        <header><h1>Food Delivery</h1></header>
        {error && <div className="alert error">{error}</div>}
        <div className="card">
          <h2>Đăng nhập</h2>
          <form onSubmit={handleLogin}>
            <input placeholder="Username" value={loginForm.username}
              onChange={e => setLoginForm({ ...loginForm, username: e.target.value })} /><br />
            <input type="password" placeholder="Password" value={loginForm.password}
              onChange={e => setLoginForm({ ...loginForm, password: e.target.value })} /><br />
            <button type="submit">Đăng nhập</button>
          </form>
          <p style={{ margin: '16px 0 8px' }}>Tài khoản demo: demo / 123456</p>
        </div>
        <div className="card">
          <h2>Đăng ký</h2>
          <form onSubmit={handleRegister}>
            <input placeholder="Username" value={registerForm.username}
              onChange={e => setRegisterForm({ ...registerForm, username: e.target.value })} /><br />
            <input placeholder="Họ tên" value={registerForm.fullName}
              onChange={e => setRegisterForm({ ...registerForm, fullName: e.target.value })} /><br />
            <input type="password" placeholder="Password" value={registerForm.password}
              onChange={e => setRegisterForm({ ...registerForm, password: e.target.value })} /><br />
            <button type="submit">Đăng ký</button>
          </form>
        </div>
      </div>
    )
  }

  return (
    <div>
      <header>
        <h1>Food Delivery</h1>
        <div>
          <span>{user.fullName || user.username}</span>
          <button className="secondary" onClick={handleLogout} style={{ marginLeft: 8 }}>Đăng xuất</button>
        </div>
      </header>

      {message && <div className="alert info">{message}</div>}
      {error && <div className="alert error">{error}</div>}

      <div className="tabs">
        <button className={tab === 'foods' ? 'active' : ''} onClick={() => setTab('foods')}>Món ăn</button>
        <button className={tab === 'orders' ? 'active' : ''} onClick={() => setTab('orders')}>Đơn hàng</button>
        <button className={tab === 'notifications' ? 'active' : ''} onClick={() => setTab('notifications')}>Thông báo</button>
      </div>

      {tab === 'foods' && (
        <div className="food-grid">
          {foods.map(food => (
            <div key={food.id} className="food-item">
              <img src={food.imageUrl} alt={food.name} />
              <h3>{food.name}</h3>
              <p>{food.description}</p>
              <p className="price">{Number(food.price).toLocaleString('vi-VN')} đ</p>
              <button onClick={() => handleOrder(food)}>Đặt hàng</button>
            </div>
          ))}
        </div>
      )}

      {tab === 'orders' && (
        <div className="card">
          <h2>Đơn hàng của tôi</h2>
          <button onClick={loadOrders} style={{ marginBottom: 12 }}>Làm mới</button>
          {orders.length === 0 && <p>Chưa có đơn hàng</p>}
          {orders.map(o => (
            <div key={o.id} style={{ padding: '12px 0', borderBottom: '1px solid #eee' }}>
              <strong>#{o.id}</strong> - {o.foodName} x{o.quantity}
              <br />Tổng: {Number(o.totalAmount).toLocaleString('vi-VN')} đ
              <br /><span className={`status ${o.status}`}>{o.status}</span>
            </div>
          ))}
        </div>
      )}

      {tab === 'notifications' && (
        <div className="card">
          <h2>Thông báo</h2>
          <button onClick={loadNotifications} style={{ marginBottom: 12 }}>Làm mới</button>
          {notifications.length === 0 && <p>Chưa có thông báo (chờ thanh toán thành công)</p>}
          {notifications.map((n, i) => (
            <div key={i} style={{ padding: '8px 0' }}>{n.message}</div>
          ))}
        </div>
      )}
    </div>
  )
}
