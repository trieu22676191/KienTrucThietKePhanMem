const express = require('express');
const mysql = require('mysql2/promise');

const app = express();
const port = 3000;

app.get('/', async (req, res) => {
  try {
    const connection = await mysql.createConnection({
      host: 'mysql',
      user: 'user',
      password: 'password',
      database: 'mydb'
    });
    const [rows, fields] = await connection.execute('SELECT "Connected to MySQL successfully!" AS message');
    res.json(rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

app.listen(port, () => {
  console.log(`App running at http://localhost:${port}`);
});
