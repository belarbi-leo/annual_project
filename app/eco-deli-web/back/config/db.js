const mongoose = require("mongoose")

mongoose
    .connect('mongodb+srv://' + process.env.DB_USER_PSWD + '@eco-deli-cluster.563ai.mongodb.net/',
        {
            useNewUrlParser: true,
            useUnifiedTopology: true,
            useCreateIndex: true,
            useFindAndModify: false
        }
    )
    .then(() => console.log("Connected to MongoDB"))
    .catch((err) => console.log("Failed to connect to MongoDB", err))