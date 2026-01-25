const express = require('express')
const jwt = require('jsonwebtoken')
const crypto = require('crypto')

const app = express()
app.use(express.json())

const keyPair = crypto.generateKeyPairSync('rsa', {
    modulusLength: 2048,
    publicKeyEncoding: { type: 'spki', format: 'pem' },
    privateKeyEncoding: { type: 'pkcs8', format: 'pem' },
})

const secretKey = keyPair.privateKey
const verifyKey = keyPair.publicKey

console.log('RSA keys generated successfully!')

app.post('/login', (req, res) => {
    const payload = {id: 1, username: 'phutriue', role: 'admin'}
    const token = jwt.sign(payload, secretKey, {algorithm: 'RS256', expiresIn: '1h'})
    console.log('Token created:', token.substring(0, 30) + '...')
    res.json({accessToken: token})
})

const verifyToken = (req, res, next) => {
    const bearer = req.headers['authorization']
    const token = bearer?.replace('Bearer ', '')
    if (!token) return res.status(401).json({error: "Missing Token!"})
    try {
        req.user = jwt.verify(token, verifyKey, { algorithms: ['RS256'] })
        next()
    } catch (err) {
        return res.status(403).json({error: "Invalid token!"})
    }
}

app.get('/account', verifyToken, (req, res) => {
    console.log('User:', req.user.username)
    res.json({
        message: "Successfully access!",
        userInfo: req.user
    })
})

app.listen(3000, () => console.log('Server is running http://localhost:3000'))
