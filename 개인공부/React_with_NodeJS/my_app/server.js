const fs = require('fs');
const express = require('express');
var cookieParser = require('cookie-parser');
const bodyParser = require('body-parser');
const router = express.Router();
const crypto = require('crypto');
const app = express();
const port = process.env.PORT || 5000;
const session = require('express-session');
const FileStore = require('session-file-store')(session); // 1

app.use(cookieParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

const multer = require('multer');
const upload = multer({dest:'./upload'})

/*db info*/
const data = fs.readFileSync('./database.json');
const conf = JSON.parse(data);
const mysql = require('mysql');

app.use(session({ 
    secret: 'keyboard cat',  // 암호화
    resave: false,
    saveUninitialized: true,
    store: new FileStore()
  }));

const connection = mysql.createConnection({
    host: conf.host,
    user: conf.user,
    password: conf.password,
    port: conf.port,
    database: conf.database
});
    
connection.connect()

app.get('/get_session', (req, res) => {
    console.log('불러온 세션 : '+req.session.logined+','+req.session.user_id)
    
    res.send(req.session);                         
                
});

app.get('/api/profile', (req, res) => {
    let sql = 'SELECT * FROM CUSTOMER WHERE id = ?';
    let params = [req.session.user_id.replace('admin','')];
    
    connection.query(sql, params,
        (err, rows, fields) => {
            console.log(rows)
            res.send(rows);
        }
    )
});

app.get('/api/admins', (req, res) => {
    connection.query(
        'SELECT * FROM CUSTOMER WHERE isDeleted = 0',   
        (err, rows, fields) => {    
            res.send(rows);    
        }    
    )
});
    
app.use('/image',express.static('./upload'));

app.post('/api/admins',upload.single('image'),(req,res) =>{
    console.log(req.body)
    let sql = 'INSERT INTO CUSTOMER VALUES (null, ?, ?, ?, ?, ?, now(), 0, ?)';
    let image =''
    if(req.file === undefined)
        image = '/image/user.png';    
    else
        image = '/image/' + req.file.filename;
    let name = req.body.name;
    let birthday = req.body.birthday;
    let gender = req.body.gender;
    let job = req.body.job;    
    let hashPassword = crypto.createHash("sha512").update('1111').digest("hex");    
    let params = [image, name, birthday, gender, job,hashPassword];
    console.log('something inserted!!')

    connection.query(sql, params,
        (err, rows, fields) => {
            res.send(rows);
        }
    )
});

app.delete('/api/admins/:id', (req, res) => {
    let sql = 'UPDATE CUSTOMER SET isDeleted = 1 WHERE id = ?';
    let params = [req.params.id];
    connection.query(sql, params,
        (err, rows, fields) => {
            res.send(rows);
        }
    )
});

app.post('/auth/login',upload.single('image'),(req,res) =>{
    let body = req.body;
    let id = body.userId;
    let split_id = '';

    if(!id.indexOf('admin')){
        split_id = id.replace('admin','')
    }

    let sql = 'SELECT * FROM CUSTOMER WHERE id = ?';
    let params = [split_id];
    connection.query(sql, params,
        (err, rows, fields) => {
            console.log(rows.length)
            if(rows.length < 1){  
                res.send('아이디 불일치'); 
            }
            else{
                let dbPassword = rows[0].PASSWORD
                let inputPassword = body.passwd;
                let hashPassword = crypto.createHash("sha512").update(inputPassword).digest("hex");

                if(dbPassword === hashPassword){
                    console.log("비밀번호 일치");
                    //res.redirect("/user");
                    req.session.logined = true;
                    req.session.user_id = id;

                    console.log('비밀번호 일치, session 생성 : '+req.session)
                    res.send('로그인 성공');
                }
                else{
                    console.log("비밀번호 불일치");
                    res.send('비밀번호 불일치');
                }
            }       
        }    
    )    
});

app.get('/logout' ,function(req, res){
    sess = req.session;
    req.session.destroy(function(err){
        if(err){
            console.log(err);
        }
    })
})

app.get('/api/journal', (req, res) => {
    connection.query(
        'SELECT * FROM JOURNAL WHERE isDeleted = 0',   
        (err, rows, fields) => {    
            res.send(rows);    
        }    
    )
});

app.post('/api/journal',upload.single('image'),function(req,res) {
    console.log(req.body)
    
    let sql = 'INSERT INTO JOURNAL VALUES (null, ?, ?, ?, ?,0)';
    
    let userId = req.session.user_id;
    let title = req.body.title;
    let date_arr = req.body.date.split(' ')
    let date = date_arr[3]+'/'+date_arr[1]+'/'+date_arr[2];
    let content = req.body.content;    
    
    let params = [userId, title, date, content];
    console.log('something inserted!!')

    connection.query(sql, params,
        (err, rows, fields) => {
            res.send(rows);
        }
    )
    
});

app.listen(port, () => console.log(`Listening on port ${port}`));