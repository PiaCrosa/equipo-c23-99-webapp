import React, { ReactNode } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { useAuthProvider } from '../context/AuthProvider';
import Swal from 'sweetalert2';
import { routeList } from '../helpers/routeList';

interface ProtectedRouteProps {
	children: ReactNode;
}

const PrivateRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
	const { user } = useAuthProvider();
	const location = useLocation();

<<<<<<< HEAD
	// Busca la ruta actual en routeList para determinar su tipo
	const currentRoute = routeList.find(
		(route) => route.path === location.pathname,
	);

=======
>>>>>>> 8232e11 (re-render bucle fixed)
	if (!user) {
		Swal.fire({
			icon: 'warning',
			title: '¡Acceso denegado!',
			text: 'Debes iniciar sesión para acceder a esta página.',
			confirmButtonText: 'Aceptar',
		});

		// Redirige al login en caso de no estar logueado
		return <Navigate to='/login' />;
	}

	if (
		currentRoute &&
		currentRoute.routeType !== user.role.toLowerCase() &&
		currentRoute.routeType !== 'logged'
	) {
		Swal.fire({
			icon: 'error',
			title: 'Acceso restringido',
			text: 'No tienes permisos para acceder a esta página.',
			confirmButtonText: 'Aceptar',
		});
		return <Navigate to='/login' />;
	}

	return <>{children}</>;
};

export default PrivateRoute;
