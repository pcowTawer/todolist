import express from "express"
import db from "./queries.js"
import cors from "cors"

const app = express()
const port = 5000
app.use(express.json())
app.use(cors())

app.get('/tasks', db.getTasks)
app.post('/tasks', db.createTask)
app.put('/tasks/:id', db.updateTask)
app.delete('/tasks/:id', db.deleteTask)
app.listen(port, () => {
  console.log(`App running on port ${port}.`)
})