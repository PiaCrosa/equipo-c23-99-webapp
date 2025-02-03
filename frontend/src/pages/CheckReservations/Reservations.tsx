import { FaEye, FaTrashAlt } from 'react-icons/fa';
import { Reservation } from '../../models/teacher/ReservationGetUser';
import { revertDate } from '../../utils/stringReverse';
import { useNavigate } from 'react-router-dom';
import { UseReservations } from '../../helpers/hooks/UseReservations';

interface DataReservationProps {
	dataReservations: Reservation[] | null;
	changeModal?: (value: boolean) => void;
	changeIndiReservations?: (value: Reservation[] | null) => void;
}

export const Reservations: React.FC<DataReservationProps> = ({
	dataReservations,
	changeModal,
	changeIndiReservations,
}) => {
	const navigate = useNavigate();

	const { deleteReservation } = UseReservations();

	const handleEditReservation = (id: number) => {
		navigate(`/edit-reservation/${id ?? ''}`);
	};

	const handleDeleteReservation = async (id: number) => {
		try {
			console.log(id);
			await deleteReservation(id);
			navigate('/teacher-dashboard');
		} catch (error) {
			console.error('Error eliminando la reserva:', error);
		}
	};

	const handleViewReservation = (reservation: Reservation[] | undefined) => {
		if (changeModal && changeIndiReservations) {
			changeModal(true);
			if (reservation) {
				changeIndiReservations(reservation);
			}
		}
		return;
	};
	return (
		<div>
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
						{dataReservations?.map((reserva) => (
							<tr key={reserva.reserveId} className='hover:bg-sky-100'>
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
									{reserva.mergedReserves ? (
										reserva.mergedReserves.length > 1 ? (
											<button
												onClick={() =>
													handleViewReservation(reserva.mergedReserves)
												}
												className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
												<FaEye size={20} />
											</button>
										) : (
											<>
												<button
													onClick={() =>
														handleEditReservation(reserva.reserveId)
													}
													className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
													Editar
												</button>
												<button
													onClick={() =>
														handleDeleteReservation(reserva.reserveId)
													}
													className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
													<FaTrashAlt size={20} color='white' />
												</button>
											</>
										)
									) : (
										<>
											<button
												onClick={() => handleEditReservation(reserva.reserveId)}
												className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
												Editar
											</button>
											<button
												onClick={() =>
													handleDeleteReservation(reserva.reserveId)
												}
												className='bg-sky-500 hover:bg-sky-700 text-white font-bold py-2 px-4 rounded ml-2'>
												<FaTrashAlt size={20} color='white' />
											</button>
										</>
									)}
								</td>
							</tr>
						))}
					</tbody>
				</table>
			</div>
		</div>
	);
};
