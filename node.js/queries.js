import pg from 'pg'

const { Pool } = pg
const pool = new Pool({
  user: 'sa',
  host: 'localhost',
  database: 'tasks',
  password: '',
  port: 1521,
});

// const initialize = async () => {
//     try {
//       await client.connect();
//       console.log('Connected to DB.');
//       await client.query(
//           "CREATE TABLE IF NOT EXISTS tasks ("
//         + "id SERIAL PRIMARY KEY,"
//         + "title VARCHAR NOT NULL,"
//         + "description VARCHAR NOT NULL,"
//         + "completed BIT)");
//     } catch (err) {
//       console.log(err);
//     } finally {
//       await client.end();
//     }
// };
// initialize();

const getTasks = async (request, response) => {
    pool.query('SELECT * FROM tasks ORDER BY id ASC', (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).json(results.rows)
    })
}

const createTask = (request, response) => {
    const { title, description, completed } = request.body

    pool.query(
        'INSERT INTO tasks (title, description, completed) VALUES ($1, $2, $3)', 
        [title, description, completed], 
        (error, results) => {
        if (error) {
          throw error
        }
        response.status(201).send(`Task added`)
    })
}

const updateTask = (request, response) => {
    const id = request.params.id
    const { title, description, completed } = request.body
  
    pool.query(
        'UPDATE tasks SET title = $1, description = $2, completed = $3 WHERE id = $4',
        [title, description, completed, id],
        (error, results) => {
            if (error) {
                throw error
            }
            response.status(200).send(`Task modified with ID: ${id}`)
        }
    )
}

const deleteTask = (request, response) => {
    const id = request.params.id
  
    pool.query('DELETE FROM tasks WHERE id = $1', [id], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Task deleted with ID: ${id}`)
    })
  }

export default {getTasks, createTask, updateTask, deleteTask}


  

  
