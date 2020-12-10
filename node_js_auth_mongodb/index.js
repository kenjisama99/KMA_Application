const { MongoClient } = require("mongodb");
//var ObjectID = 
 
// Replace the following with your Atlas connection string                                                                                                                                        

const url = "mongodb+srv://kenjisama:ayanami@cluster0.akt6g.mongodb.net/test?retryWrites=true&w=majority";

const client = new MongoClient(url);
 
 // The database to use
 const dbName = "node-android";
                      
 async function run() {
    try {
         await client.connect();
         console.log("Connected correctly to server");
         const db = client.db(dbName);

         // Use the collection "people"
         const col = db.collection("users");

         // Construct a document                                                                                                                                                              
         let userDocument = {
             "phone": "0334311435",
             "password": "04071999"
         }

         // Insert a single document, wait for promise so we can read it back
         //const p = await col.insertOne(userDocument);
         // query
         var query = { "phone": "0334311435"};
         // Find one document
         const myDoc = await col.find(query);
         // Print to the console
         console.log(myDoc);

        } catch (err) {
         console.log(err.stack);
     }
 
     finally {
        await client.close();
    }
}

run().catch(console.dir);