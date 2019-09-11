import React from 'react';
import { render } from 'react-dom';
// 3rd party libraries
import "../node_modules/bootstrap/dist/css/bootstrap.css";
import "../node_modules/input-moment/dist/input-moment.css";
import "jquery/dist/jquery.min";
import "bootstrap/dist/js/bootstrap.min";

import App from './App';

// setup fake backend
import { configureFakeBackend } from './_helpers';
configureFakeBackend();

render(<App />,
    document.getElementById('root')
);