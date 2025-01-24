import React, { useEffect, useState } from 'react';
import { AdminHomeTitle } from './AdminHomeTitle';
import { AdminHomeData } from './AdminHomeData';
import AdminHomeButton from './AdminHomeButton';
import { useNavigate } from 'react-router-dom';

const AdminDashboard: React.FC = () => {
	const navigate = useNavigate();
	const [AdminInfo, setAdminInfo] = useState({
		full_name_admin: 'julito',
		dniAdmin: '96104483',
		email_admin: 'julioandresrivas@gmail.com',
		password_admin: 'Jarg*0901',
	});

	const [institutionInfo, setInstitutionInfo] = useState({
		cue: '20000000',
		name: 'La Escuelita',
		educational_level: 'Primario',
		address: 'san martin 455',
		email: 'utn@gmail.com',
		phone: '1234567890',
		website: 'http://utn.com',
	});

	useEffect(() => {}, []);

	return (
		<React.Fragment>
			<AdminHomeTitle>{institutionInfo.name}</AdminHomeTitle>
			<AdminHomeData admin={AdminInfo} institution={institutionInfo} />
			<AdminHomeButton
				onClick={() => navigate('/edit-admin-profile')}
				text='Editar Perfil'
			/>
		</React.Fragment>
	);
};

export { AdminDashboard };
