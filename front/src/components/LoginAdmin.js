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



export class LoginAdmin extends React.Component{

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

    handleChangePasswd(e) {
        this.setState({
            password : e.target.value
        })
    }

    handleSend() {
    
        var url = "http://localhost:8080"
        Axios.post(url+"/usuarios/Y3Jpc3RpYW4gZXMgZ2F5",{email:this.state.email,password:this.state.password})
        .then((data)=>{
            console.log("entre");
            localStorage.setItem("IsLoggedIn",true);
            this.setState(this.state);

        }).catch((err)=>{
            console.log(err);
            alert("No se pudo iniciar sesion");
        });
    }
    
    render(){
        
        if(localStorage.getItem("IsLoggedIn")){
            return <Redirect to="/login"></Redirect>
        }
    
        return (
            <React.Fragment>
                <CssBaseline />
                <main className="layout">
                    <Paper className="paper">
                        <Avatar className="avatar">
                            <LockIcon />
                        </Avatar>
                        <Typography variant="h2">Sign in</Typography>
                        <div>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="email">Email Address</InputLabel>
                                <Input 
                                id="email" 
                                name="email" 
                                autoComplete="email" 
                                onChange={this.handleChangeEmail}
                                autoFocus />
                            </FormControl>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="password">Password</InputLabel>
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
                                    Sign in
                                </Button>
                                <Button
                                    variant="outlined"
                                    type ="submit"
                                    fullWidth
                                    color="primary"
                                    href = "/signup"
                                >
                                    Sign Up
                                </Button>
                            </div>
                        </div>
                    </Paper>
                </main>
            </React.Fragment>
        );
    }

}