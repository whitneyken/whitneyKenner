extern puts
section .text  ; this says that we're about to write code (as opposed to data)
section .rodata  ; this is the read only data (hello is a constant)
helloString: db "hello",0 ; hellostring is the name of our symbol
; db is the directive to put data in our object file
; the 0 is the null terminator that puts is expecting
; nasm does NOT null terminate our string automatically

global sayHello ; this says that "sayHello" is a symbol that the linker needs to know about
global myPuts


sayHello:           ;and here we go...
push rbp ;push rbp onto the stack, making it 8 byte aligned
mov rdi, helloString ; move our char* (pointing to hello) to the right place as specified by the ABI
call puts ; call puts
pop rbp ;pop it off the stack so that the caller's return address is now on top of the stack
ret ; return
  ;;code goes here

  ret                 ; just return

myPuts:
mov rax, 0x1
mov rdi, 0x1
mov rsi, helloString
mov rdx, 0x5
syscall
ret
