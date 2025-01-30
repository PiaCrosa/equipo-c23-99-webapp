import { useEffect } from 'react';
import { useAuthProvider } from '../../context/AuthProvider';

interface useGetCurrentTokenProps {
  onUpdateToken: (tokenValue: string) => void;
}

const useGetCurrentToken = (
  { onUpdateToken }: useGetCurrentTokenProps
) => {
  const context = useAuthProvider();

  useEffect(() => {
    onUpdateToken(context.user?.jwtToken || '');
  }, [onUpdateToken, context]);

}

export {
  useGetCurrentToken,
}