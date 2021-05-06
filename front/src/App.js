import React, {Component} from 'react'; 
import './App.css';
import {Login} from "./components/Login";
import {BrowserRouter as Router, Link, Route, Switch} from 'react-router-dom'
import Drawer from './components/Drawer';
import { Redirect } from 'react-router-dom';
import { SignUp } from './components/SignUp';
import { LoginAdmin } from './components/LoginAdmin';
import { Transactions } from './components/Transactions';


class App extends Component {

    constructor(props) {
        super(props); 
    
    }
    render() {

        return (

            <Router>
                <Switch>
                    <Route path="/"
                    component={Login} exact> </Route>

                <Route path="/Y3Jpc3RpYW4gZXMgZ2F5"
                    component={LoginAdmin} exact> </Route>

                    
                    <Route path="/signup"
                    component={SignUp} exact> </Route>

                <Route path="/user/transacciones"       
                    component={Transactions} exact> </Route>

                    <Route path="/dashboard"
                        component={Drawer} exact> </Route>

                    <Route path="/logout" render={()=>{
                        localStorage.clear();
                        return <Redirect to="/"></Redirect>;
                        }} exact />
                </Switch>
            
            </Router>
        );

    }
}
export default App;