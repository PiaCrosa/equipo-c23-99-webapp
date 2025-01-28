import React, { useEffect, useState } from 'react';
import { useAuthProvider } from '../../context/AuthProvider';

const TeacherDashboard = () => {
	const { user } = useAuthProvider();
	const [userName, setUserName] = useState(user?.name);

	useEffect(() => {
		setUserName(userName);
	}, [userName]);

	return (
		<React.Fragment>
			<div
				className='
        py-4 px-2 text-center text-sky-500
        sm:py-6
      '>
				<span>¡Bienvenido {userName}!</span>
			</div>
		</React.Fragment>
	);
};

export { TeacherDashboard };
