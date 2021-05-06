import React from 'react';
import OutlinedCard from './OutlinedCard';

export class Todo extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
            <OutlinedCard
                numerodecuenta = {this.props.numerodecuenta} 
                saldo = {this.props.saldo} 
                tipodecuenta = {this.props.tipodecuenta}>
            </OutlinedCard>
            <br/>
            </div>
        );
    }
}