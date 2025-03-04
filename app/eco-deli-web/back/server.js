require('dotenv').config({path: './config/.env'})
require('.config/db')

const express = require('express')
const app = express()

app.listen(process.env.PORT, () => console.log(`Listening on port ${process.env.PORT}`))