import React, { Component } from 'react';
import './TodoApp.css';
import { TodoList } from "./TodoList";
import 'react-datepicker/dist/react-datepicker.css';
import moment from "moment";
import Dialog from './Dialog';
import AddIcon from '@material-ui/icons/Add';
import Fab from '@material-ui/core/Fab';
import SearchIcon from '@material-ui/icons/Search';
import CancelRoundedIcon from '@material-ui/icons/CancelRounded';
import Axios from 'axios';
import { v4 as uuidv4 } from 'uuid';



export class TodoApp extends Component {



    constructor(props) {
        super(props);
        this.state = {
            items: [],
            numerodecuenta: '', saldo: '', tipodecuenta: '',cuentadestino:'', detalle : '',isOpen: false
        };
        this.state.itemsFiltered = [{ status: "", dueDate: moment(), responsible: "" }];
        this.state.itemsShow = [];

    }

    componentDidMount() {
        var url = "http://localhost:8080/usuarios/cuenta";

        Axios.get(url, {
            params:
            {
                cedula: localStorage.getItem("cedula")
            }
        }).then(response => {
            console.log(response.data)
            this.setState({
                items : response.data
            })
        }
        ).catch(e => {
            console.log(e);
            alert(e);
        });

    }


    render() {

        return (

            <div className="App">
                <TodoList todoList={this.state.items} />
                <Dialog
                    handleNumeroCuentaChange={this.handleNumeroCuentaChange}
                    handleCuentaDestinoChange={this.handleCuentaDestinoChange}
                    handleDetalleChange={this.handleDetalleChange}
                    handleSaldoChange={this.handleSaldoChange}
                    handleTipoCuentaChange={this.handleTipoCuentaChange}
                    handleSubmit={this.handleSubmit}
                    handleCancel={this.handleCancel}
                    handleOpen={this.handleOpen}
                    items={this.state.items}
                    open={this.state.isOpen}
                    state={this.state}>
                </Dialog>

                <Fab aria-label='Add' onClick={() => this.handleOpen()} color='primary' style={{ right: '-45%' }}>
                    <AddIcon />
                </Fab>
            </div>
        );
    }

    handleCancel = () => {
        this.setState({
            numerodecuenta: '',
            saldo: '',
            tipodecuenta: '',
            cuentadestino: '',
            detalle: ''
        });
        this.handleOpen();
    }


    handleOpen = () => {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }

    handleNumeroCuentaChange = (e) => {
        this.setState({
            numerodecuenta: e.target.value
        });
    }

    handleCuentaDestinoChange = (e) => {
        this.setState({
            cuentadestino: e.target.value
        });
    }

    handleDetalleChange = (e) => {
        this.setState({
            detalle: e.target.value
        });
    }


    handleSaldoChange = (e) => {
        this.setState({
            saldo: e.target.value
        });
    }

    handleTipoCuentaChange = (e) => {
        this.setState({
            tipocuenta: e.target.value
        });
    }


    handleSubmit = (e) => {

        var url = "http://localhost:8080/transacciones/registro"
    
        Axios.post(url,
            {
                identificador : uuidv4(),
                origen : this.state.numerodecuenta,
                destinatario : this.state.cuentadestino,
                saldoatransferir : this.state.saldo,
                detalle : this.state.detalle,
                aprobacion : true,
                intrabancaria : true}
            ).then(response => {
                alert("transacción exitosa");
                return;
            }).catch(error => {
                console.log(error);
                alert("Transacción fallida")
            })


        this.setState({
            numerodecuenta: '',
            saldo: '',
            tipodecuenta: '',
            cuentadestino: '',
            detalle: ''
        });

        this.handleOpen();
    
    }

}

