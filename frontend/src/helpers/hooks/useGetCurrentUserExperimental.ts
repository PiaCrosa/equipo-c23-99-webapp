// TODO: Darle la logica real para verificar usuarios

import { useEffect, useState } from 'react';
import { RouteType } from '../RolesType';
import { ExperimentalUser } from '../../models/ExperimentalUser';
import { useAuthProvider } from '../../context/AuthProvider';

interface UseGetCurrentUserProps {
	onUpdateUser: (user: ExperimentalUser) => void;
}

const UseGetCurrentUser = ({ onUpdateUser }: UseGetCurrentUserProps) => {
	const { user } = useAuthProvider();
	const [currentRole, setCurrentRole] = useState<RouteType>(
		user?.role.toLocaleLowerCase() as RouteType,
	);

	useEffect(() => {
		setCurrentRole(user?.role.toLocaleLowerCase() as RouteType);
		onUpdateUser({ role: currentRole });
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [currentRole, user]);
};

export { UseGetCurrentUser };
