import React, { Component } from 'react';
import Axios from 'axios';
import Typography from '@material-ui/core/Typography';
import { Redirect, Link, withRouter } from 'react-router-dom';
import CreateOutlinedIcon from '@material-ui/icons/CreateOutlined';
import DialogSaldo from './DialogSaldo';
import Fab from '@material-ui/core/Fab';

export class Accounts extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            cuentas : [], open : false, cuentac: ""
        };
    }

    
    componentDidMount() {
        var url = "http://localhost:8080/cuentas/all";

        Axios.get(url, {
            params:
            {
                userquery: localStorage.getItem("cedula")
            }
        }).then(response => {
            this.setState({
                cuentas : response.data
            })
        }
        ).catch(e => {
            localStorage.setItem("IsLoggedIn2",false);
            localStorage.removeItem("IsLoggedIn2");
            this.setState(this.state);
        });

    }

    handleOpen = (cuenta) => {
        this.setState({
            cuetac : cuenta,
            open : !this.state.open
        })
    }

    handleSubmit = (e) => {

        var url = "http://localhost:8080/cuentas"
        Axios.patch(url+"/"+this.state.cuentac)
          .then((data)=>{
              alert("Se actualizó el saldo con éxito");
              this.componentDidMount();
          }).catch((err)=>{
              console.log(err);
              alert("No se pudo actualizar el saldo");
          });

        this.setState({
            cuentac: '',
            isOpen : !this.state.open
        
        });

    }


    handleCancel = () => {
        this.setState({
            cuentac: '',
            open : !this.state.open
        });
    }

    render() {

        if(!localStorage.getItem("IsLoggedIn2")){
            return <Redirect to="/"></Redirect>
        }

        return (
            <div style={{'text-align' : 'center'}}> 
            <DialogSaldo
            handleSaldoChange={this.handleSaldoChange}
            handleSubmit={this.handleSubmit}
            handleCancel={this.handleCancel}
            handleOpen={this.handleOpen}
            cuentac={this.state.cuentac}
            open={this.state.open}
            state={this.state}
            ></DialogSaldo>
            <Typography  variant="h4" noWrap>
                Cuentas activas
          </Typography>
          <br/>
          <br/>
        
            <table> 
            <thead>
            <tr>
                <th>Tipo de Cuenta</th>
                <th>Usuario</th>
                <th>Saldo</th>
                <th>N° cuenta</th>
            </tr>   
            </thead>

            <tbody>

            
                {this.state.cuentas.map((t) => {
                    return (
                    <tr>
                        <td>{t.tipodecuenta}</td>
                        <td>{t.usuario}</td>
                        <td>{t.saldo} &nbsp;&nbsp;
                        <Fab size="small" color="primary" aria-label="edit"  onClick={(e) => this.handleOpen(t.numerodecuenta)}>
                            <CreateOutlinedIcon ></CreateOutlinedIcon>
                        </Fab>
                        </td>
                        <td>{t.numerodecuenta}</td>
                    </tr>);})}

            </tbody>

            </table>
            </div>
        
        );
    }
} 