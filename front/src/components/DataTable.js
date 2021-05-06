import * as React from 'react';
import { DataGrid } from '@material-ui/data-grid';
import Axios from 'axios';

const columns = [
  { field: 'id', headerName: 'Fecha', width: 120 },
  { field: 'firstName', headerName: 'Origen', width: 100 },
  { field: 'lastName', headerName: 'Destino', width: 100 },
  {
    field: 'age',
    headerName: 'Saldo',
    type: 'number',
    width: 100,
  },
  {
    field: 'fullName',
    headerName: 'Detalle',
    width: 180
  }
];


export default function Table(props) {

  return (
    <p> {props.state.transacciones}</p>
  );
}