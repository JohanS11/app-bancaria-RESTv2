import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
  root: {
    minWidth: 275,
    textAlign: "center",
  },
  bullet: {
    display: 'inline-block',
    margin: '0 2px',
    transform: 'scale(0.8)',
  },
  title: {
    fontSize: 14,
  },
  pos: {
    marginBottom: 12,
  },
});

export default function OutlinedCard(props) {
  const classes = useStyles();
  const bull = <span className={classes.bullet}>•</span>;
  return (
    <Card className={classes.root} variant="outlined">
      <CardContent>
        <Typography variant="h5" component="h5">
            N° Cuenta:  {props.numerodecuenta}
        </Typography>
        <Typography className={classes.pos} variant="h6">
            Saldo: $ {props.saldo}
        </Typography>
        <Typography fontWeight="Bold" variant="h6" component="p">
          Tipo de cuenta : {props.tipodecuenta}  
        </Typography>
      </CardContent>
    </Card>
  );
}