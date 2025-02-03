import React, { useEffect, useState } from 'react';
import { UseReservations } from '../../helpers/hooks/UseReservations';
import { Reservation } from '../../models/teacher/ReservationGetUser';
import ModalCheckReservations from './ModalCheckReservations';
import { Reservations } from './Reservations';
import { revertDate } from '../../utils/stringReverse';

const CheckReservations: React.FC = () => {
	const [dataReservations, setDataReservations] = useState<
		Reservation[] | null
	>(null);
	const [indiReservations, setIndiReservations] = useState<
		Reservation[] | null
	>(null);
	const [isModalOpen, setIsModalOpen] = useState(false);
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

	return (
		<div className='bg-gray-100 shadow-md rounded border border-gray-200 px-8 pt-8 pb-8 mb-4 m-8'>
			<h2 className='text-4xl font-medium leading-6 text-sky-600 mb-4'>
				Mis Reservas
			</h2>
			<Reservations
				dataReservations={dataReservations}
				changeModal={setIsModalOpen}
				changeIndiReservations={setIndiReservations}
			/>
			<ModalCheckReservations
				isOpen={isModalOpen}
				onClose={() => setIsModalOpen(false)}
				title={`Reservas del ${indiReservations ? revertDate(indiReservations[0].date) : ''}`}>
				<Reservations dataReservations={indiReservations} />
			</ModalCheckReservations>
		</div>
	);
};

export { CheckReservations };
