import React from 'react';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import Slide from '@material-ui/core/Slide';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import MenuItem from '@material-ui/core/MenuItem';
import Select from '@material-ui/core/Select';
import {
    MuiPickersUtilsProvider,
    KeyboardDatePicker
  } from '@material-ui/pickers'

const Transition = React.forwardRef(function Transition(props, ref) {
  return <Slide direction="up" ref={ref} {...props} />;
});

export default function AlertDialogSlide(props) {

  return (
    <div>
      <Dialog
        open={props.open}
        TransitionComponent={Transition}
        keepMounted
        onClose={()=> props.handleOpen()}
        aria-labelledby="alert-dialog-slide-title"
        aria-describedby="alert-dialog-slide-description"
      >
        <DialogTitle id="alert-dialog-slide-title">{"Nueva Transacción"}</DialogTitle>
        <DialogContent>
                <form onSubmit={props.handleSubmit} className="todo-form">
      
                    <label htmlFor="detalle"  className="right-margin">
                        Detalle
                    </label>
                    <TextField
                        multiline 
                        variant="outlined"
                        id="detalle"
                        onChange={props.handleDetalleChange}
                        value={props.state.detalle}>
                    </TextField>
                    <br/>
                    <br/>
                    <label htmlFor="cuenta" className="right-margin">
                        N° Cuenta Origen
                    </label>
                    <br/>
                    <br/>

                    <TextField 
                      id="outlined-basic"
                      style= {{width : '75%'}}
                      variant="outlined"
                      select
                      value={props.state.numerodecuenta}
                      onChange={props.handleNumeroCuentaChange}
                    >
                     {props.state.items.map((cuentas) => (
                      <MenuItem key={cuentas.numerodecuenta} value={cuentas.numerodecuenta}> 
                        {cuentas.numerodecuenta} 
                      </MenuItem>
                      ))} 
                      </TextField>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
                    <label htmlFor="cuentadestino" className="right-margin">
                        N° Cuenta destino:
                    </label>

                    <TextField
                        id="cuentadestino"
                        onChange={props.handleCuentaDestinoChange}
                        value={props.state.cuentadestino}>
                    </TextField>
                    <br/>
                    <br/>
                    <br/>
                    <label htmlFor="saldo" className="right-margin">
                        Monto a transferir:
                    </label>

                    <TextField
                        id="saldo"
                        type="int"
                        onChange={props.handleSaldoChange}
                        value={props.state.saldo}>
                    </TextField>
  
                    <br/>
                </form>
                <br/>
                <br/>
        </DialogContent>
        <DialogActions>
          <Button onClick={()=> props.handleCancel()} color="primary">
            Cancelar
          </Button>
          <Button onClick={props.handleSubmit} color="primary">
            Enviar
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}