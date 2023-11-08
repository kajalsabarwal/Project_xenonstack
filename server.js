const express = require('express');
const bodyParser = require('body-parser');
const mysql = require('mysql2');
const cors = require('cors'); // Add this line
app.use(cors());

const app = express();
const port = 3000;

// Body parser middleware
app.use(bodyParser.json());

// MySQL database connection
const db = mysql.createConnection({
    host: '127.0.0.1',
    user: 'root',
    password: 'kajal',
    database: 'car_dealership',
});

db.connect((err) => {
    if (err) {
        console.error('Error connecting to database:', err);
        return;
    }
    console.log('Connected to database');
});

// Serve HTML file
app.use(express.static('public'));

// Handle signup endpoint
app.post('/signup', (req, res) => {
    const { name, dob, contact, email, password } = req.body;

    // Insert user data into the database
    const sql = 'INSERT INTO users (name, dob, contact, email, password) VALUES (?, ?, ?, ?, ?)';
    db.query(sql, [name, dob, contact, email, password], (err, result) => {
        if (err) {
            console.error('Error inserting user data:', err);
            return res.status(500).json({ success: false, message: 'Error signing up. Please try again later.' });
        }

        console.log('User signed up successfully');
        res.status(200).json({ success: true, message: 'User signed up successfully.' });
    });
});

// Start the server
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
