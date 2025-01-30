export interface Reservation {
	userName: string;
	date: string;
	reservationShiftStatus: string;
	selectedTimeSlot: string;
	resourceName: string;
	reservationStatus: string;
}

export interface ReservationResponse {
	data: {
		content: Reservation[];
		totalPages: number;
		totalElements: number;
		size: number;
		number: number;
	};
	message: string;
	status: string;
}
