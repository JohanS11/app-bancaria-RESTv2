import React from 'react';
import {Todo} from './Todo'

export class TodoList extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        const todoList = this.props.todoList.map((todo, i) => {
            return (
                <Todo key={i} numerodecuenta={todo.numerodecuenta} saldo={todo.saldo} tipodecuenta={todo.tipodecuenta}/>
            );
        });

        return (
            todoList
        );


    }

}