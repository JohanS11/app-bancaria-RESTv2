import React from 'react';
import TextField from '@material-ui/core/TextField';
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import FormControl from '@material-ui/core/FormControl';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import LockIcon from '@material-ui/icons/LockOutlined';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import './Login.css'
import Drawer from './Drawer';
import Axios from 'axios';
import nextId from "react-id-generator";
import { Redirect, withRouter} from 'react-router-dom';

export class SignUp extends React.Component{

    constructor(props){
        super(props);
        this.state = {apellido : "" , nombre : "" , celular: "", email:"", cedula: "",password:"",confirmPassword:""};
    }
    
    handleChangeEmail = (e) => {
        this.setState({
            email : e.target.value
        });
    }

    handleChangeNombre = (e) => {
        this.setState({
            nombre : e.target.value
        });
    }

    handleChangeCelular = (e) => {
        this.setState({
            celular : e.target.value
        });
    }

    handleChangeCedula = (e) => {
        this.setState({
            cedula : e.target.value
        });
    }

    handleChangeApellido = (e) => {
        this.setState({
            apellido : e.target.value
        });
    }


    handleChangePasswd = (e) => {
        this.setState({
            password : e.target.value
        })
    }

    handleConfirm = (e) => {
        this.setState({
            confirmPassword : e.target.value
        })
    }

    handleSend = () =>{
        
        var url = "http://localhost:8080"
        console.log(nextId("USER-ID-"))
        Axios.post(url+"/usuarios/registro-app",{
            apellido: this.state.apellido,
            nombre: this.state.nombre,
            celular: this.state.celular,
            correo: this.state.email,
            cedula: this.state.cedula,
            usuarioid: nextId("USER-ID-"), // id: test-id-2
            pwd: this.state.password,
            rol: "CLIENTE",
        })
        .then((data)=>{
            localStorage.setItem("IsLoggedIn",true);
            this.setState(this.state);
        }).catch((err)=>{
            console.log(err);
            alert("Este usuario no está registrado en el sistema bancario");
        });
    }

    render(){
        if(localStorage.getItem("IsLoggedIn")){
            return <Redirect to="/dashboard"></Redirect>
        }
        
        return(
            <React.Fragment>
                <CssBaseline />
                <main className="layout">
                    <Paper className="paper">
                        <Avatar className="avatar">
                            <LockIcon />
                        </Avatar>
                        <Typography variant="h5">Registro Applicacion</Typography>
                    
                        <div>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="apellido">Apellido</InputLabel>
                                <Input
                                    name="apellido"
                                    id = "apellido"
                                    onChange={this.handleChangeApellido}
                                    autoComplete="apellido"
                                    required
                                />
                            </FormControl>

                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="nombre">Nombre</InputLabel>
                                <Input
                                    name="nombre"
                                    id = "nombre"
                                    onChange={this.handleChangeNombre}
                                    autoComplete="nombre"
                                    required
                                />
                            </FormControl>

                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="celular">Celular</InputLabel>
                                <Input
                                    name="celular"
                                    id = "celular"
                                    onChange={this.handleChangeCelular}
                                    autoComplete="celular"
                                    required
                                />
                            </FormControl>

                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="email">Email Address</InputLabel>
                                <Input 
                                required
                                id="email" 
                                name="email" 
                                autoComplete="email" 
                                onChange={this.handleChangeEmail}
                                autoFocus />
                            </FormControl>

                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="cedula ">Cedula</InputLabel>
                                <Input
                                    name="cedula"
                                    id = "cedula"
                                    onChange={this.handleChangeCedula}
                                    autoComplete="cedula"
                                    required
                                />
                            </FormControl>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="password">Password</InputLabel>
                                <Input
                                    required
                                    name="password"
                                    id="password"
                                    type="password"
                                    onChange={this.handleChangePasswd}
                                    autoComplete="current-password"
                                />
                            </FormControl>
                            <FormControl margin="normal" required fullWidth>
                                <InputLabel htmlFor="cpassword">Confirm Password</InputLabel>
                                <Input
                                    required
                                    name="cpassword"
                                    id="cpassword"
                                    type="password"
                                    onChange ={this.handleConfirm}
                                    autoComplete="current-password"
                                />
                            </FormControl>
                            
                            <Button onClick = {() => this.handleSend()}
                                type = "submit"
                                fullWidth
                                variant="raised"
                                color="primary">
                                Registrarse
                            </Button>
                        
                        </div>
                    </Paper>
                </main>
            </React.Fragment>
        )
    }
}

export default withRouter(SignUp);