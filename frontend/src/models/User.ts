export interface User {
  dni: string,
  full_name: string,
  email: string,
  password: string,
  password2: string,
  role: 'TEACHER' | 'ADMIN',
  institution_cue: string,
}
