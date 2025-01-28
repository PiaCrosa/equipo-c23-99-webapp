import React, { useState } from 'react';
import { AdminHomeTitle } from './AdminHomeTitle';
import { AdminHomeData } from './AdminHomeData';
import AdminHomeButton from './AdminHomeButton';
import { useNavigate } from 'react-router-dom';
import { adminData } from '../../helpers/data/admin';

const AdminDashboard: React.FC = () => {
	const navigate = useNavigate();
	// adminData es un objeto de prueba
	const [AdminInfo] = useState(adminData);

	return (
		<React.Fragment>
			<AdminHomeTitle>{AdminInfo.name}</AdminHomeTitle>
			<AdminHomeData adminFull={AdminInfo} />
			<AdminHomeButton
				onClick={() => navigate('/edit-admin-profile')}
				text='Editar Perfil'
			/>
		</React.Fragment>
	);
};

export { AdminDashboard };
