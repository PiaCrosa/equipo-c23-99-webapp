// src/App.tsx
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import Register from './pages/Register';
import Login from './pages/Login';

import './App.css';
import { Layout } from './components/Layout';
import { routeList } from './helpers/routeList';
import { UserProvider } from './context/AuthProvider';

const App: React.FC = () => {
	return (
		<UserProvider>
			<Router>
				<Routes>
					<Route path='/' element={<LandingPage />} />
					<Route path='/register' element={<Register />} />
					<Route path='/login' element={<Login />} />
					<Route element={<Layout />}>
						{routeList.map((route) => (
							<Route
								key={route.path}
								path={route.path}
								element={<route.element />}
							/>
						))}
					</Route>
				</Routes>
			</Router>
		</UserProvider>
	);
};

export default App;
