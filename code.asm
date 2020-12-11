DATA SEGMENT
	prixTtc DD
	prixHt DD
DATA ENDS
CODE SEGMENT
	mov eax, 200
	push eax
	pop eax
	mov prixHt, eax
	push eax
	pop eax
	mov eax, prixHt
	mov ebx, 119
	push ebx
	pop ebx
	pop eax
	mul eax, ebx
	push eax
	mov ebx, 100
	push ebx
	pop ebx
	pop eax
	div eax, ebx
	push eax
	pop eax
	mov prixTtc, eax
	push eax
CODE ENDS
