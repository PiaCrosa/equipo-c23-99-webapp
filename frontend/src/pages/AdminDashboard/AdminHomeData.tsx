import { useEffect, useState } from 'react';
import { PropsAdminHomeData } from '.';
import { getAdminData } from '../../services/adminRequest';
import { getInstitutionData } from '../../services/institutionRequest';
import { useAuthProvider } from '../../context/AuthProvider';
import { AdminGet } from '../../models/admin/AdminGet';

export const AdminHomeData: React.FC<PropsAdminHomeData> = ({ adminFull }) => {
	const { user } = useAuthProvider();

	const [admin, setAdmin] = useState<AdminGet | null>(null);
	const [institution, setInstitution] = useState<any | null>(null);

	useEffect(() => {
	const fetchData = async () => {
	  if (user?.name) {
		// Obtener datos del administrador
		const adminData = await getAdminData(user.name, user.jwtToken);
		setAdmin(adminData);

		// Obtener datos de la institución usando el CUE
		if (adminData?.nameSchool) {
		  const institutionData = await getInstitutionData(adminData.nameSchool, user.jwtToken);
		  setInstitution(institutionData);
		}
	  }
	};

	fetchData();
	}, [user?.name, user?.jwtToken]);

	console.log('Admin:', admin);
  	console.log('Institution:', institution);
	

	return (
		<>
			<div className='bg-gray-100 shadow-md rounded border border-gray-200 px-8 pt-8 pb-8 mb-4 m-8'>
				<h2 className='text-4xl font-medium leading-6 text-sky-600'>
					Administrador
				</h2>
				<div className='text-xl mt-4 ml-8 text-cyan-600'>
					<p className='pt-2'>Nombre: {admin?.fullName}</p>
					<p className='pt-2'>DNI: {admin?.dni}</p>
					<p className='pt-2'>Email: {admin?.email}</p>
					<p className='pt-2'>
						Password:
						<input
							type='password'
							value={admin?.email ?? ''}
							className='pl-2'
							disabled
						/>
					</p>
				</div>

				<h2 className='text-4xl mt-8 font-medium leading-6 text-sky-600 mt-4'>
					Institución
				</h2>
				<div className='text-xl mt-4 ml-8 text-cyan-600'>
					<p className='pt-2'>CUE: {institution?.cue}</p>
					<p className='pt-2'>Institución: {institution?.name}</p>
					<p className='pt-2'>Nivel Educativo: {institution?.educational_level}</p>
					<p className='pt-2'>Direccion: {institution.address}</p>
					<p className='pt-2'>Telefono: {institution.phone}</p>
					<p className='pt-2'>
						Sitio Web:
						<a
							className='ml-2 text-sky-700 hover:text-blue-700 underline'
							target='_blank'
							href={adminFull.website}>
							{adminFull.website}
						</a>
					</p>
				</div>
			</div>
		</>
	);
};
