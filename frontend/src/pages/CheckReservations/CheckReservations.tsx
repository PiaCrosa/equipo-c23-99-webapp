import React from 'react';
import { FaTrashAlt } from 'react-icons/fa';
import { reservasDePrueba } from '../../helpers/data/reservations';

const CheckReservations: React.FC = () => {
	const handleEditReservation = () => {
		console.log('Editar reserva:');
	};

	const handleDeleteReservation = () => {
		console.log('Eliminar reserva:');
	};

	return (
		<div className='bg-gray-100 shadow-md rounded border border-gray-200 px-8 pt-8 pb-8 mb-4 m-8'>
			<h2 className='text-4xl font-medium leading-6 text-sky-600 mb-4'>
				Mis Reservas
			</h2>
			<div className='overflow-x-auto overflow-y-auto h-[400px]'>
				<table className='min-w-full divide-y divide-gray-200 rounded-md shadow-md'>
					<thead>
						<tr>
							<th className='px-6 py-3 bg-gray-100 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
								Equipo
							</th>
							<th className='px-6 py-3 bg-gray-100 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
								Fecha
							</th>
							<th className='px-6 py-3 bg-gray-100 text-left text-xs font-medium text-gray-500 uppercase tracking-wider'>
								Horario
							</th>
							<th className='px-6 py-3 bg-gray-100'></th>
						</tr>
					</thead>
					<tbody className='bg-white divide-y divide-gray-200'>
						{reservasDePrueba.map((reserva) => (
							<tr key={reserva.fecha} className='hover:bg-sky-100'>
								<td className='px-6 py-4 text-left text-base font-medium text-sky-700'>
									{reserva.equipo}
								</td>
								<td className='px-6 py-4 text-left text-base font-medium text-sky-700'>
									{reserva.fecha}
								</td>
								<td className='px-6 py-4 text-left text-base font-medium text-sky-700'>
									{reserva.horaInicio} - {reserva.horaFin}
								</td>
								<td className='px-6 py-4 whitespace-nowrap text-right text-sm font-medium flex justify-end'>
									<button
										onClick={() => handleEditReservation()}
										className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
										Editar
									</button>
									<button
										onClick={() => handleDeleteReservation()}
										className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
										<FaTrashAlt size={20} color='white' />
									</button>
								</td>
							</tr>
						))}
					</tbody>
				</table>
			</div>
		</div>
	);
};

export { CheckReservations };
