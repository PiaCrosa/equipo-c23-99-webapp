import React, { useEffect, useState } from 'react';
import { FaTrashAlt } from 'react-icons/fa';
import { UseReservations } from '../../helpers/hooks/UseReservations';
import { revertDate } from '../../utils/stringReverse';

export interface ReservationsData {
	userName: string;
	date: string;
	reservationShiftStatus: string;
	selectedTimeSlot: string;
	resourceName: string;
	reservationStatus: string;
}

const CheckReservations: React.FC = () => {
	const [dataReservations, setDataReservations] = useState<
		ReservationsData[] | null
	>(null);
	const { getReservationTeacher, mergeConsecutiveReservations } =
		UseReservations();

	useEffect(() => {
		const fetchReservations = async () => {
			const booking = await getReservationTeacher();
			if (booking) {
				setDataReservations(mergeConsecutiveReservations(booking));
			}
		};
		fetchReservations();
	}, [getReservationTeacher, mergeConsecutiveReservations]);

	const handleEditReservation = (id: number) => {
		console.log(`Editar reserva: ${id}`);
	};

	const handleDeleteReservation = (id: number) => {
		console.log(`Eliminar reserva: ${id}`);
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
							<th className='px-6 py-3 bg-gray-100 text-center text-xs font-medium text-gray-500 uppercase tracking-wider'>
								Equipo
							</th>
							<th className='px-6 py-3 bg-gray-100 text-center text-xs font-medium text-gray-500 uppercase tracking-wider'>
								Fecha
							</th>
							<th className='px-6 py-3 bg-gray-100 text-center text-xs font-medium text-gray-500 uppercase tracking-wider'>
								Horario
							</th>
							<th className='px-6 py-3 bg-gray-100'></th>
						</tr>
					</thead>
					<tbody className='bg-white divide-y divide-gray-200'>
						{dataReservations?.map((reserva, id) => (
							<tr key={id} className='hover:bg-sky-100'>
								<td className='px-6 py-4 text-center text-base font-medium text-sky-700'>
									{reserva.resourceName}
								</td>
								<td className='px-6 py-4 text-center text-base font-medium text-sky-700'>
									{revertDate(reserva.date)}
								</td>
								<td className='px-6 py-4 text-center text-base font-medium text-sky-700'>
									<span className='block text-sm'>Desde - Hasta</span>
									<span className='block text-sm'>
										{reserva.selectedTimeSlot}
									</span>
								</td>
								<td className='px-6 py-4 whitespace-nowrap text-right text-sm font-medium flex justify-end items-center'>
									<button
										onClick={() => handleEditReservation(id)}
										className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
										Editar
									</button>
									<button
										onClick={() => handleDeleteReservation(id)}
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
