import React, { Component } from 'react';
import Axios from 'axios';
import Typography from '@material-ui/core/Typography';

export class Transactions extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            transacciones : []
        };
    }

    
    componentDidMount() {
        var url = "http://localhost:8080/usuarios/transacciones";

        Axios.get(url, {
            params:
            {
                usuario: localStorage.getItem("cedula")
            }
        }).then(response => {
            this.setState({
                transacciones : response.data
            })
        }
        ).catch(e => {
            console.log(e);
            alert(e);
        });

    }

    render() {

        return (
            <div style={{'text-align' : 'center'}}> 
            <Typography  variant="h4" noWrap>
                Transacciones
          </Typography>
          <br/>
          <br/>
        
            <table> 
            <thead>
            <tr>
                <th>Fecha</th>
                <th>Origen</th>
                <th>Destinatario</th>
                <th>Valor</th>
                <th>Detalle</th>
            </tr>   
            </thead>

            <tbody>

            
                {this.state.transacciones.map((t) => {
                    return (
                    <tr>
                        <td>{t.fecha}</td>
                        <td>{t.origen}</td>
                        <td>{t.destinatario}</td>
                        <td>{t.saldoatransferir}</td>
                        <td>{t.detalle}</td>
                    </tr>);})}
            </tbody>

            </table>
            </div>
        
        );
    }
} 