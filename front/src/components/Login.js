import React from 'react';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import LockIcon from '@material-ui/icons/LockOutlined';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import Axios from 'axios';
import './Login.css'
import { Redirect, Link, withRouter } from 'react-router-dom';



export class Login extends React.Component{

    constructor(props) {
        super(props);
        this.state = {email:"", password:""};
        this.handleChangeEmail = this.handleChangeEmail.bind(this)
        this.handleChangePasswd = this.handleChangePasswd.bind(this);
        this.handleSend = this.handleSend.bind(this);
    }

    handleChangeEmail(e) {
        this.setState({
            email : e.target.value
        });
    }

    sanitizeHTML = (str) => {
        return str.replace(/[^\w. ]/gi, function (c) {
            return '&#' + c.charCodeAt(0) + ';';
        });
    }

    handleChangePasswd(e) {
        this.setState({
            password : e.target.value
        })
    }

    handleSend() {

        var url = "http://localhost:8080"
        console.log(this.sanitizeHTML(this.state.email));
        
        Axios.post(url+"/usuarios/ingreso",{email:this.state.email,password:this.state.password})
        .then((data)=>{
            
            localStorage.setItem("email",data.data[0]);
            localStorage.setItem("username",data.data[1]);
            localStorage.setItem("cedula",data.data[2]);
            
            if (data.data[3] === "admin") {
                console.log("entresadsa");
                localStorage.setItem("IsLoggedIn2",true);
                this.setState(this.state);
            } else{
                localStorage.setItem("IsLoggedIn",true);
                this.setState(this.state);
            }
       
        
        }).catch((err)=>{
            alert("Usuario o contrase??a inv??lidos");
        });
    }
    
    render(){
        
        if(localStorage.getItem("IsLoggedIn2")){
            return <Redirect to="/dashboard-admin"></Redirect>
        }

        else if(localStorage.getItem("IsLoggedIn")){
            return <Redirect to="/dashboard"></Redirect>
        }
    
        return (
            <React.Fragment>
                <CssBaseline />
                <main className="layout">
                    <Paper className="paper">
                        <Avatar className="avatar">
                            <LockIcon />
                        </Avatar>
                        <Typography variant="h2">Ingresar</Typography>
                        <div>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="email">Correo</InputLabel>
                                <Input 
                                id="email" 
                                name="email" 
                                autoComplete="email" 
                                onChange={this.handleChangeEmail}
                                autoFocus />
                            </FormControl>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="password">Contrase??a</InputLabel>
                                <Input
                                    name="password"
                                    id="password"
                                    type="password"
                                    onChange={this.handleChangePasswd}
                                    autoComplete="current-password"
                                />
                            </FormControl>
                            <div style={{display:'flex'}}>
                                <Button onClick={this.handleSend}
                                    variant="outlined" 
                                    color="primary"
                                    type = "submit"
                                    fullWidth
                                >
                                    Entrar
                                </Button>
                                <Button
                                    variant="outlined"
                                    type ="submit"
                                    fullWidth
                                    color="primary"
                                    href = "/signup"
                                >
                                    Registrarse
                                </Button>
                            </div>
                        </div>
                    </Paper>
                </main>
            </React.Fragment>
        );
    }

}