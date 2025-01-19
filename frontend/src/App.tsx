// src/App.tsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import Register from './pages/Register';
import Login from './pages/Login';
import './App.css';
import { Layout } from './components/Layout';
import { routeList } from './helpers/routes';

const pathRoutes = routeList;

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LandingPage />} />
        <Route path="/register" element={<Register />} />
        <Route path="/login" element={<Login />} />

        {/* ADMIN ROUTES */}
        <Route element={<Layout />}>
          {
            pathRoutes.filter(
              route=>route.routeType === 'admin'
            ).map(route => {
              return (
                <Route
                  key={route.path}
                  path={route.path}
                  element={<route.element />}
                />
              )
            })
          }
        </Route>

        {/* TEACHER ROUTES */}
        <Route element={<Layout />}>
          {
            pathRoutes.filter(
              route=>route.routeType === 'teacher'
            ).map(route => {
              return (
                <Route
                  key={route.path}
                  path={route.path}
                  element={<route.element />}
                />
              )
            })
          }
        </Route>



      </Routes>
    </Router>
  );
};

export default App;
