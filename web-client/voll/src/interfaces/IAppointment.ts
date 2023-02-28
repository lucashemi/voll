export default interface IAppointment {
    patient: string,
    patientName: string,
    doctor: string,
    doctorName: string,
    specialty: string,
    date: string,
    time: string,
    reasonForCancellation: string,
    id: number
}