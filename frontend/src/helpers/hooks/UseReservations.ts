import { useParams } from 'react-router-dom';
import { useAuthProvider } from '../../context/AuthProvider';
import { ReservationsData } from '../../pages/CheckReservations/CheckReservations';
import { getReservationsByUser } from '../../services/teacher/reservationsRequest';
import { useCallback } from 'react';

export const UseReservations = () => {
	const { user } = useAuthProvider();
	const { page } = useParams<{ page: string }>();

	const getReservationTeacher = useCallback(async () => {
		let pageNumber = 0;
		if (page) {
			pageNumber = parseInt(page);
		}
		console.log(page);
		if (user) {
			const { dni, jwtToken } = user;
			const reservationsResponse = await getReservationsByUser(
				dni,
				pageNumber,
				jwtToken,
			);
			const reservations = reservationsResponse?.data.content;
			return reservations;
		}
	}, [user, page]);

	const mergeConsecutiveReservations = useCallback(
		(reservations: ReservationsData[]): ReservationsData[] => {
			const grouped: { [key: string]: ReservationsData[] } = {};

			reservations.forEach((reserva) => {
				const key = `${reserva.userName}-${reserva.date}-${reserva.resourceName}-${reserva.reservationShiftStatus}`;
				if (!grouped[key]) grouped[key] = [];
				grouped[key].push(reserva);
			});

			const mergedReservations: ReservationsData[] = [];

			Object.values(grouped).forEach((group) => {
				group.sort((a, b) => {
					const startA = parseInt(a.selectedTimeSlot.split('-')[0], 10);
					const startB = parseInt(b.selectedTimeSlot.split('-')[0], 10);
					return startA - startB;
				});

				let currentMerge = { ...group[0] };

				for (let i = 1; i < group.length; i++) {
					const prevEnd = parseInt(
						currentMerge.selectedTimeSlot.split('-')[1],
						10,
					);
					const currentStart = parseInt(
						group[i].selectedTimeSlot.split('-')[0],
						10,
					);
					const currentEnd = parseInt(
						group[i].selectedTimeSlot.split('-')[1],
						10,
					);

					if (prevEnd === currentStart) {
						currentMerge.selectedTimeSlot = `${currentMerge.selectedTimeSlot.split('-')[0]}-${currentEnd}`;
					} else {
						mergedReservations.push(currentMerge);
						currentMerge = { ...group[i] };
					}
				}
				mergedReservations.push(currentMerge);
			});

			// formatea los horarios, añade un "0" delante si es un solo digito
			mergedReservations.forEach((reserva) => {
				reserva.selectedTimeSlot = reserva.selectedTimeSlot
					.split('-')
					.map((hour) => (hour.length === 1 ? `0${hour}` : hour))
					.join('-');
			});

			return mergedReservations;
		},
		[],
	);

	return {
		getReservationTeacher,
		mergeConsecutiveReservations,
	};
};
